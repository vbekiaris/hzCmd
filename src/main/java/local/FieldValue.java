package local;

import java.util.Arrays;
import java.util.List;

public class FieldValue {
        public String field;
        public String value;

        public FieldValue(String field, String value) {
            this.field = field;
            this.value = value;
        }

        public List<String> getValues() {
            return Arrays.asList(value.split(","));
        }

        @Override
        public String toString() {
            return "FieldValue{" +
                    "field='" + field + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
}