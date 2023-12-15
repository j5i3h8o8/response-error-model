package com.example.response.controller;

import com.example.response.ApiResponse;
import com.example.response.entity.Student;
import com.example.response.exception.CustomExceptionV1;
import com.example.response.exception.ErrorCode;
import com.example.response.exception.data.InputRestriction;
import com.example.response.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MyController extends BaseController {
    private final StudentService studentService;

    /**
     * fixme: POSTMapping 이 맞으나 테스트 편의를 위해서 Get 으로 선언함.
     */
    @GetMapping("/student/{name}/{grade}")
    public ApiResponse<Student> add(@PathVariable("name") String name, @PathVariable("grade") int grade) {
        if (grade > 5) {
            throw new CustomExceptionV1(
                    ErrorCode.BAD_REQUEST,
                    "grade 는 6이상 입력 할 수 없습니다.",
                    new InputRestriction(6)
            );
        }
        return makeResponse(studentService.addStudent(name, grade));
    }

    @GetMapping("/student/ordered")
    public ApiResponse<Student> getOrdered() {
        return makeResponse(studentService.getOrderedStudents());
    }

    @GetMapping("/student/{grade}")
    public ApiResponse<Student> get(@PathVariable("grade") int grade) {
        return makeResponse(studentService.getGradeStudent(grade));
    }
}
