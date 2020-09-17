package com.yanghyeonjin.hellokotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.yanghyeonjin.hellokotlin.databinding.ActivityCameraAndCaptureBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraAndCaptureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraAndCaptureBinding

    private val REQUEST_IMAGE_CAPTURE = 1 // 카메라 사진 촬영 요청코드
    private lateinit var curPhotoPath: String // 문자열 형태의 사진 경로 값

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraAndCaptureBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 퍼미션
        setPermission()

        binding.btnOpenCamera.setOnClickListener {
            takeCapture() // 기본 카메라 앱을 실행하여 사진 촬영
        }
    }

    /**
     * ted permission 설정
     */
    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() {
                // 설정해놓은 위험권한들이 허용 되었을 경우 이 곳을 수행함.
                Toast.makeText(this@CameraAndCaptureActivity, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                // 설정해놓은 위험권한들 중 거부를 한 경우 이 곳을 수행함.
                Toast.makeText(this@CameraAndCaptureActivity, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        // 권한 허용 팝업 띄우기
        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라를 사용하기위해 권한이 필요합니다.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요.")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()
    }

    /**
     * 카메라 촬영
     */
    private fun takeCapture() {
        // 카메라 앱을 실행한다.
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.yanghyeonjin.hellokotlin.fileprovider",
                        it
                    )

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    /**
     * 이미지 파일 생성
     */
    private fun createImageFile(): File? {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREAN).format(Date())

        // ? -> 변수가 null 일 수 있다라는 것을 프로그램에게 알림.
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
            .apply { curPhotoPath = absolutePath }
    }


    // startActivityForResult로 실행한 기본 카메라 앱의 결과 값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지 성공적으로 가져왔다면
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap
            val file = File(curPhotoPath)

            // 안드로이드 9.0 (Pie) 버전보다 낮을 경우
            if (Build.VERSION.SDK_INT < 28) {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                binding.ivCaptureImage.setImageBitmap(bitmap)
            } else {
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                binding.ivCaptureImage.setImageBitmap(bitmap)
            }

            savePhoto(bitmap)
        }
    }

    /**
     * 갤러리에 사진 저장
     */
    private fun savePhoto(bitmap: Bitmap) {
        // 사진 폴더 경로
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/"
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREAN).format(Date())
        val filename = "${timestamp}.jpeg"
        val folder = File(folderPath)

        // 현재 해당 경로의 폴더가 존재하는지 검사
        if (!folder.isDirectory) {
            folder.mkdirs() // make directory 하세요
        }

        // 실제로 저장하는 부분
        val out = FileOutputStream(folderPath + filename)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }
}