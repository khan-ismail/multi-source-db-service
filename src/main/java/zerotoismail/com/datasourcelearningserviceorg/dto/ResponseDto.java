package zerotoismail.com.datasourcelearningserviceorg.dto;


import lombok.*;

public class ResponseDto {
   private String statusCode;
   private String message;

   public ResponseDto(){}

   public ResponseDto(String statusCode, String message) {
      this.statusCode = statusCode;
      this.message = message;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(String statusCode) {
      this.statusCode = statusCode;
   }
}
