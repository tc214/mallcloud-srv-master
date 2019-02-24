package org.tc.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Perm.
 */
@Data
public class Perm implements Serializable {

    private static final long serialVersionUID = 7230382287449339371L;
    private String resource;
    private String perm;

}
