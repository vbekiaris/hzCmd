package local.bench;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldValue {
        public String field;
        public List<String> values = new ArrayList();

        public FieldValue(String field, String value) {
            this.field = field;

            for (String v : value.split(",")) {
                values.add(v);
            }
        }

        public List<FieldValue>explode(){
            List<FieldValue> explode = new ArrayList();

            for (String value : values) {
                explode.add(new FieldValue(field, value));
            }

            return explode;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        if(o instanceof FieldValue) {
            FieldValue that = (FieldValue) o;

            if (field != null ? !field.equals(that.field) : that.field != null) return false;
            return !(values != null ? !values.equals(that.values) : that.values != null);
        }

        if(o instanceof String){
            String thatFiled = (String) o;
            return this.field.equals(thatFiled);
        }

        return false;
    }



    @Override
    public String toString() {

        if(values.size()==1){
            return field +"="+ values.get(0);
        }
        return field +"="+ values;
    }
}