package com.example.ex86_retrofitimageupload;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {

    // 7_ 이미지 파일은 택배상자(MultipartBody.Part)에 넣어서 전송함.
    // @Part 어노테이션을 사용한다. 단, @Multipart 어노테이션이 필요하다.
    @Multipart
    @POST("Retrofit/fileUpload.php")
    Call<String> uploadImage( @Part MultipartBody.Part file );
}
