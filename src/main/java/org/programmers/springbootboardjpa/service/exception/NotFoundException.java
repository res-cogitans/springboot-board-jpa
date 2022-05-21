package org.programmers.springbootboardjpa.service.exception;

public class NotFoundException extends IllegalArgumentException {

    //TODO: 하위 예외로 분화하는 것에 대해 고민
    public NotFoundException(Long id,  String type) {
        super("지정된 userId 값: " + id + " 에 해당하는 " + type + "을(를) 찾을 수 없습니다.");
    }
}