package meabh.GsonTypeAdapter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String dateString = in.nextString();
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public void write(JsonWriter out, LocalDate date) throws IOException {
        out.value(formatter.format(date));
    }

}
