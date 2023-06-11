package de.note.app.io.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SignedInUserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private @NonNull Long id;
    private @NonNull String username;
    private List<NoteDto> notes = new ArrayList<>();
    private @NonNull String jwtToken;


}
