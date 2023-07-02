package com.imdadareeph.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mohd Imdad Areeph
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails
{
    private String errorCode;
    private String errorMessage;
}
