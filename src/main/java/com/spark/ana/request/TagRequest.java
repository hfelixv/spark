package com.spark.ana.request;

//import com.oracle.oal.g2m.consumptionsearchservice.types.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class TagRequest {




    @Parameter(name = "option", in = ParameterIn.QUERY, description = "Flag to identify option", required = false)
    private Long option = 0L;
}
