package org.tc.provider.model.dto.user;

import org.tc.base.dto.LoginAuthDto;
import org.tc.provider.model.vo.MenuVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The class Login response dto.
 */
@Data
@ApiModel(value = "登录后的返回参数Dto")
public class LoginRespDto implements Serializable {
    private static final long serialVersionUID = -8992761897550131632L;

    @ApiModelProperty(value = "登录信息")
    private LoginAuthDto loginAuthDto;

    @ApiModelProperty(value = "菜单集合")
    private List<MenuVo> menuList;
}
