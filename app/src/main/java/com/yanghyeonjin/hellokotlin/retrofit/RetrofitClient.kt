package com.yanghyeonjin.hellokotlin.retrofit

import android.util.Log
import com.yanghyeonjin.hellokotlin.util.Constants.TAG
import com.yanghyeonjin.hellokotlin.util.Key
import com.yanghyeonjin.hellokotlin.util.isJsonArray
import com.yanghyeonjin.hellokotlin.util.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

// 싱글턴 (메모리를 하나만 쓴다)
object RetrofitClient {
    // 레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.e(TAG, "RetrofitClient - getClient() called")



        // okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        // 통신 로그를 찍기위해 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e(TAG, "RetrofitClient - log() called | message: $message")

                when {
                    message.isJsonObject() -> {
                        Log.e(TAG, JSONObject(message).toString(4))
                    }
                    message.isJsonArray() -> {
                        Log.e(TAG, JSONArray(message).toString(4))
                    }
                    else -> {
                        try {

                        } catch (e: Exception) {
                            Log.e(TAG, message)
                        }
                    }
                }
            }
        })
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // body 레벨의 로그를 보여준다.

        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)



        // 기본 파라미터 추가
        val baseParameterInterceptor: Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.e(TAG, "RetrofitClient - intercept() called")

                // 오리지날 리퀘스트
                val originalRequest = chain.request()

                // 파라미터 추가하기
                val addedUrl = originalRequest.url.newBuilder()
                    .addQueryParameter("client_id", Key.UNSPLASH_CLINET_ID)
                    .build()

                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                return chain.proceed(finalRequest)
            }
        })
        client.addInterceptor(baseParameterInterceptor)


        // 커넥션 타임아웃 설정
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)

        // 실패했을 때 다시 요청할 것인지?
        client.retryOnConnectionFailure(true)

        

        // 레트로핏 빌더를 통해 인스턴스 생성
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                // 위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                .client(client.build())

                .build()
        }

        return retrofitClient
    }

}