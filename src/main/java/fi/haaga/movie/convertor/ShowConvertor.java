package  fi.haaga.movie.convertor;

import  fi.haaga.movie.entities.Show;
import  fi.haaga.movie.request.ShowRequest;

public class ShowConvertor {

    public static Show showDtoToShow(ShowRequest showRequest) {
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();

        return show;
    }
}
