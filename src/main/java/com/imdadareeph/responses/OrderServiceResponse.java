package com.imdadareeph.responses;

import com.imdadareeph.error.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceResponse implements Serializable {

    private static final long serialVersionUID = -7969641997432317345L;


    private String name;

    private ErrorDetails errorData;

}
