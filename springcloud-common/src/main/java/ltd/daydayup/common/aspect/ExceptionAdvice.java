package ltd.daydayup.common.aspect;

import lombok.extern.slf4j.Slf4j;
import ltd.daydayup.common.enums.BaseResultCodeEnum;
import ltd.daydayup.common.exception.ApiException;
import ltd.daydayup.common.result.Result;
import ltd.daydayup.common.utils.FileSizeUtils;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @auther lipengcheng
 * @date 2021/12/4 15:22
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionAdvice {

    /**
     * 将异常在Controller捕获转换成Result对象返回
     *
     * @param ex
     * @see Result
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleAllException(Exception ex) {
        log.error("ExceptionAdvice.handleAllException", ex);
        // 返回统一的error Result
        return Result.buildErrorResult(BaseResultCodeEnum.SYSTEM_ERROR.getCode(), BaseResultCodeEnum.SYSTEM_ERROR.getMsg());
    }

    @ExceptionHandler(ApiException.class)
    public Result<Object> handleApiException(ApiException ex) {
        log.error("ExceptionAdvice.handleApiException", ex);
        Result<Object> result = new Result<>(false);
        result.setCode(ex.getCode() + "");
        result.setMsg(ex.getMessage());
        result.setData(ex.getData());
        // 返回统一的error Result
        return result;
    }

    /**
     * 处理ConstraintViolationException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class, BindException.class})
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        log.warn("ExceptionAdvice.handleConstraintViolationException=>{}", message);
        Result result = new Result(false);
        result.setMsg("参数不合法:" + message);
        result.setCode(BaseResultCodeEnum.ILLEGAL_ARGUMENT.getCode());
        return result;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Result result = new Result(false);
        result.setMsg("参数不合法");
        result.setCode(BaseResultCodeEnum.ILLEGAL_ARGUMENT.getCode());
        if (ex.getBindingResult() != null && ex.getBindingResult().getFieldErrors() != null) {
            List<FieldError> errors = ex.getBindingResult().getFieldErrors();
            if (errors != null && errors.size() > 0) {
                FieldError fieldError = errors.iterator().next();
                log.warn("ExceptionAdvice.handleMethodArgumentNotValidException=>{}", fieldError.getDefaultMessage());
                result.setMsg("参数不合法:" + fieldError.getDefaultMessage());
            }
        }
        return result;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return Result.buildErrorResult(ex.getMessage());
    }

    @ExceptionHandler({MultipartException.class})
    public Result handleFileSizeLimitExceededException(MultipartException ex) {

        Result result = new Result(false);
        result.setCode(BaseResultCodeEnum.SYSTEM_FAILURE.getCode());
        if (ex.getCause().getCause() instanceof FileSizeLimitExceededException) {

            result.setMsg("文件上传大小超过限制:" + FileSizeUtils.getSizeString(((FileSizeLimitExceededException) ex.getCause().getCause()).getPermittedSize()));
        }
        return result;
    }
}
