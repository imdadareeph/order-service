package com.imdadareeph.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderServiceRequest implements Serializable {

    private static final long serialVersionUID = -7969641997432317345L;

    private String name;

}
