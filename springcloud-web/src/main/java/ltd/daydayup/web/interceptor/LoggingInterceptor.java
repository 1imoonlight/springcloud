package ltd.daydayup.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        String resBodyStr = getResponsBody(response);
        log.info(String.format("响应 :url-> %s %n contentType->%s,lenght->%s,status->%s %n 耗时 %.1fms%n headers->%s %n body->%s %n ",
                response.request().url(), response.body().contentType(), response.body().contentLength(), response.code(), (t2 - t1) / 1e6d, response.headers(), resBodyStr));
        return response;
    }

    private String getRequestBody(Request request) {
        String requestContent = "";
        if (request == null) {
            return requestContent;
        }
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return requestContent;
        }
        try {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = StandardCharsets.UTF_8;
            requestContent = buffer.readString(charset);
        } catch (IOException e) {
            log.error("okhttp3 logRequest error >> ex = {}", e);
        }
        return requestContent;
    }

    private String getResponsBody(Response response) {
        String resBodyStr = "";
        try {
            MediaType mt = response.body().contentType();
            Charset charset = mt != null && mt.charset() != null ? mt.charset() : StandardCharsets.UTF_8;
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.getBuffer();
            resBodyStr = buffer.clone().readString(charset);
        } catch (Exception e) {
            log.error("okhttp3 logResponse error >> ex = {0}", e);
        }
        return resBodyStr;
    }
}
