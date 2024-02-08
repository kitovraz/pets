package ru.elmanov.port.in.common.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class SearchByIdRequest<T> {

    T id;
}
