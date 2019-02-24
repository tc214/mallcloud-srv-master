package org.tc.core.generator;

/**
 * The inter Id generator.
 */
public interface IdGenerator {

    /**
     * 生成下一个ID
     *
     * @return the Long
     */
    Long nextId();
}
