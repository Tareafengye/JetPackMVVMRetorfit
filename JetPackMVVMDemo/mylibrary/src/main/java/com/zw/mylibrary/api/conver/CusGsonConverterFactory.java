package com.zw.mylibrary.api.conver;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api.conver
 * @ClassName: CusGsonConverterFactory
 * @Description:  转换-为了处理 数据 begin object but was array}
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CusGsonConverterFactory extends Converter.Factory {

    private final Gson gson;

    private CusGsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    public static CusGsonConverterFactory create() {
        return create(new Gson());
    }

    public static CusGsonConverterFactory create(Gson gson) {
        return new CusGsonConverterFactory(gson);
    }


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter, type);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter(gson, adapter);
    }

    private final static class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private static final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);

            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }

    private final static class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final TypeAdapter<T> adapter;
        private final Type mType;

        GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
            this.gson = gson;
            this.adapter = adapter;
            this.mType = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String body = value.string();
            try {
                JSONObject json = new JSONObject(body);
                String code = json.getString("errorCode");
                String msg = json.getString("errorMsg");

                if (code.equals("-1")) {
                    throw new CustomerException(msg, code);
                } else {
                    return gson.fromJson(body, mType);
                }

            } catch (CustomerException e) {
                throw e;
            } catch (Exception e) {
                throw new CustomerException("未知错误", "-1");
            } finally {
                value.close();
            }
        }
    }
}
