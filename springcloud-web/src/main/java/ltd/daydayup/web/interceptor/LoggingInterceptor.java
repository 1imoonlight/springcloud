package ltd.daydayup.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        log.info(String.format("请求:url-> %s %n method->%s %n headers->%s %n body->%s",
                request.url(),request.method(), request.headers(),getRequestBody(request)));
        long t1 = System.nanoTime();
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        String resBodyStr =getResponsBody(response);
        log.info(String.format("响应 :url-> %s %n contentType->%s,lenght->%s,status->%s %n 耗时 %.1fms%n headers->%s %n body->%s %n ",
                response.request().url(),response.body().contentType(),response.body().contentLength(),response.code(), (t2 - t1) / 1e6d, response.headers(),resBodyStr));

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
            Charset charset = Charset.forName("utf-8");
            requestContent = buffer.readString(charset);
        } catch (IOException e) {
            log.error("okhttp3 logRequest error >> ex = {}",e);
        }
        finally {
            return requestContent;
        }
    }

    private String getResponsBody(Response response){
        String resBodyStr="";
        try {
            MediaType mt=response.body().contentType();
            Charset charset=mt!=null&&mt.charset()!=null?mt.charset():Charset.forName("utf-8");
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.getBuffer();
            resBodyStr = buffer.clone().readString(charset);
        }catch (Exception e){
            log.error("okhttp3 logResponse error >> ex = {}",e);
        }
        finally {
            return resBodyStr;
        }
    }
}
