cmake_minimum_required(VERSION 3.4.1)

set(my_lib_path ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

add_library(avcodec SHARED IMPORTED)
set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libavcodec-57.so)

add_library(avfilter SHARED IMPORTED)
set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libavfilter-6.so)

add_library(avformat SHARED IMPORTED)
set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libavformat-57.so)

add_library(avutil SHARED IMPORTED)
set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libavutil-55.so)

add_library(swresample SHARED IMPORTED)
set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libswresample-2.so)

add_library(swscale SHARED IMPORTED)
set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libswscale-4.so)

add_library(avdevice SHARED IMPORTED)
set_target_properties(avdevice PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libavdevice-57.so)

add_library(postproc SHARED IMPORTED)
set_target_properties(postproc PROPERTIES IMPORTED_LOCATION ${my_lib_path}/libpostproc-54.so)


add_library(
        native-lib

        SHARED

        native-lib.cpp)


find_library(
        log-lib

        log)


target_link_libraries(
        native-lib

        ${log-lib}

        avcodec
        avfilter
        avformat
        avutil
        swresample
        swscale
        avdevice
        postproc
)

target_include_directories(native-lib PUBLIC "${CMAKE_CURRENT_SOURCE_DIR}/include"
        ${CMAKE_SOURCE_DIR})