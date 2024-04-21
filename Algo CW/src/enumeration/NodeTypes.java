package enumeration;
/** *****************************************************************************
 *  Name:    Kavindu Mendis
 *  UOW ID:   W1902232
 *  IIT ID: 20212152
 *
 *  Description:  node types details can be found here
 *
 **************************************************************************** */
public enum NodeTypes {
    SNOW("*",false),
    ROCK("0", true),
    START("S", false),
    FINISH("F", false),
        ;

    private final String code;
    private final boolean isBlock;

    NodeTypes(String code, boolean isBlock) {
        this.code = code;
        this.isBlock = isBlock;
    }

    public String getCode() {
        return code;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public static NodeTypes getValue(final String code) {
        NodeTypes value = null;
        for (NodeTypes i : NodeTypes.values()) {
            if (i.getCode().contentEquals(code)) {
                value = i;
                break;
            }
        }
        return value;
    }
}
