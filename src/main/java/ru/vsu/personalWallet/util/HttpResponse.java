package ru.vsu.personalWallet.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponse {
   private long timestamp;
   private int status;
   private String error;
   private String message;
   private String path;
}
