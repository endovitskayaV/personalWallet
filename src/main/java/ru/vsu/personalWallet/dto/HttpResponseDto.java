package ru.vsu.personalWallet.dto;


import lombok.EqualsAndHashCode;

//TODO: mark as @Data and solve problem with constructor and builder
@EqualsAndHashCode
public class HttpResponseDto {
   private long timestamp;
   private int status;
   private String error;
   private String message;
   private String path;

   public long getTimestamp() {
      return timestamp;
   }

   public HttpResponseDto setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
   }

   public int getStatus() {
      return status;
   }

   public HttpResponseDto setStatus(int status) {
      this.status = status;
      return this;
   }

   public String getError() {
      return error;
   }

   public HttpResponseDto setError(String error) {
      this.error = error;
      return this;
   }

   public String getMessage() {
      return message;
   }

   public HttpResponseDto setMessage(String message) {
      this.message = message;
      return this;
   }

   public String getPath() {
      return path;
   }

   public HttpResponseDto setPath(String path) {
      this.path = path;
      return this;
   }
}
