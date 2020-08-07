cmake_minimum_required(VERSION 3.13)
project(PaCE_Man_Admin_C C)
set(CMAKE_EXE_LINKER_FLAGS -static)
set(CMAKE_C_STANDARD 99)

add_executable(PaCE_Man_Admin_C PaCEManAdminC.c)