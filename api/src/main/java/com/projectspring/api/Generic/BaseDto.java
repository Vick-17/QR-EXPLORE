<<<<<<< HEAD:api/src/main/java/com/projectspring/api/Generic/BaseDto.java
package com.projectspring.api.Generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    
    private Long id;
=======
package com.projectspring.api.generic;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class BaseDto {
    private long id;
>>>>>>> origin/feat-CREATE-QRCODE:api/src/main/java/com/projectspring/api/generic/BaseDto.java
}
