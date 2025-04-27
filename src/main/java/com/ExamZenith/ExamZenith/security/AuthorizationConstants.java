package com.ExamZenith.ExamZenith.security;

public class AuthorizationConstants {
    public static final String ADMIN = "hasAuthority('ROLE_ADMIN')";
    public static final String TEACHER_OR_ADMIN = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_TEACHER')";
    public static final String STUDENT_OR_ADMIN = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_STUDENT')";

}
