package cn.hhnail.backend.enums;

public interface BaseEnum {

    /**
     * 判断是否与该枚举类等价
     * @param code 编码值。实现该接口必须有该属性
     * @return
     *
     * XXX(xxx)
     * 尽管toString可以拿到当前类的名称，即XXX，但是XXX不一定和xxx相等。
     * 两者含义不同，XXX是名称，有见名知义的作用，但是xxx仅是确定唯一性的编码。
     * 只要code相同，则表示等价与该枚举类
     *
     * 例如语言中用Chinese（XXX）表示中文，但是编码却用zh（xxx）
     */
    boolean equals(String code);

}
