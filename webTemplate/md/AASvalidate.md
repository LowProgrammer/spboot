[【返回目录】](../README.md)

# 格式校验

1. 校验注解，功能码型接口的请求可以通过校验注解，让框架自动校验请求数据

   目前支持校验器有：

  - @NotEmpty @NotNull @NotBlank 非空
  - @Min 最小
  - @Max 最大
  - @Size @Length @Range 数值区间
  - @Past 小于当前时间
  - @Future 大于当前时间
  - @Pattern 正则格式校验

```java

	@ApiModel(description = "页面访问请求体")
	public class CrashRequest extends JSONRequest {
	    @ApiModelProperty(notes = "业务数据")
	    ReqData data;

	    public ReqData getData() {
	        return data;
	    }

	    public void setData(ReqData data) {
	        this.data = data;
	    }

	    @ApiModel
	    public static class ReqData {
	        @ApiModelProperty(notes = "公共头部")
 			@NotEmpty(message = "不能为空")
			@MobilePhone
	        private BehaviorHeader header;
	        @ApiModelProperty(notes = "异常奔溃集合")
	        private List<Crash> records;

	        public BehaviorHeader getHeader() {
	            return header;
	        }

	        public void setHeader(BehaviorHeader header) {
	            this.header = header;
	        }

	        public List<Crash> getRecords() {
	            return records;
	        }

	        public void setRecords(List<Crash> records) {
	            this.records = records;
	        }
	    }
	}


```

- 自定义格式校验器

如下是手机号的格式校验注解实现

```java


		package com.apexsoft.aas.util.validation;

		import javax.validation.Constraint;
		import javax.validation.ConstraintValidator;
		import javax.validation.ConstraintValidatorContext;
		import javax.validation.Payload;
		import java.lang.annotation.ElementType;
		import java.lang.annotation.Retention;
		import java.lang.annotation.RetentionPolicy;
		import java.lang.annotation.Target;
		import java.util.regex.Matcher;
		import java.util.regex.Pattern;

		@Target({ElementType.FIELD, ElementType.METHOD})
		@Retention(RetentionPolicy.RUNTIME)
		@Constraint(validatedBy = {MobilePhone.Checker.class})
		public @interface MobilePhone {
		    String message() default "手机号格式不对";

		    Class<?>[] groups() default {};

		    Class<? extends Payload>[] payload() default {};

		    class Checker implements ConstraintValidator<MobilePhone, String> {

		        @Override
		        public void initialize(MobilePhone constraintAnnotation) {

		        }

		        @Override
		        public boolean isValid(String value, ConstraintValidatorContext context) {
		            boolean flag;
		            if (value != null) {
		                Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
		                Matcher match = p.matcher(value);
		                flag = match.matches();
		                return flag;
		            } else {
		                return true;
		            }

		        }

		    }
		}



```

[【返回目录】](../README.md)
