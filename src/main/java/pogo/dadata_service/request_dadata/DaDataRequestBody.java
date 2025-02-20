package pogo.dadata_service.request_dadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DaDataRequestBody {

    private String query;

    public static DaDataRequestBody getQueryBody(String query) {
        return DaDataRequestBody.builder()
                .query(query).build();
    }
}
