# CMAKE generated file: DO NOT EDIT!
# Generated by "NMake Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

!IF "$(OS)" == "Windows_NT"
NULL=
!ELSE
NULL=nul
!ENDIF
SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "C:\Users\OscarAraya\Desktop\PaCE Man Admin C"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles\PaCE_Man_Admin_C.dir\depend.make

# Include the progress variables for this target.
include CMakeFiles\PaCE_Man_Admin_C.dir\progress.make

# Include the compile flags for this target's objects.
include CMakeFiles\PaCE_Man_Admin_C.dir\flags.make

CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.obj: CMakeFiles\PaCE_Man_Admin_C.dir\flags.make
CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.obj: ..\PaCEManAdminC.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/PaCE_Man_Admin_C.dir/PaCEManAdminC.c.obj"
	C:\PROGRA~2\MICROS~1\2017\COMMUN~1\VC\Tools\MSVC\1415~1.267\bin\Hostx86\x86\cl.exe @<<
 /nologo $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) /FoCMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.obj /FdCMakeFiles\PaCE_Man_Admin_C.dir\ /FS -c "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\PaCEManAdminC.c"
<<

CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/PaCE_Man_Admin_C.dir/PaCEManAdminC.c.i"
	C:\PROGRA~2\MICROS~1\2017\COMMUN~1\VC\Tools\MSVC\1415~1.267\bin\Hostx86\x86\cl.exe > CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.i @<<
 /nologo $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\PaCEManAdminC.c"
<<

CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/PaCE_Man_Admin_C.dir/PaCEManAdminC.c.s"
	C:\PROGRA~2\MICROS~1\2017\COMMUN~1\VC\Tools\MSVC\1415~1.267\bin\Hostx86\x86\cl.exe @<<
 /nologo $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) /FoNUL /FAs /FaCMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.s /c "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\PaCEManAdminC.c"
<<

# Object files for target PaCE_Man_Admin_C
PaCE_Man_Admin_C_OBJECTS = \
"CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.obj"

# External object files for target PaCE_Man_Admin_C
PaCE_Man_Admin_C_EXTERNAL_OBJECTS =

PaCE_Man_Admin_C.exe: CMakeFiles\PaCE_Man_Admin_C.dir\PaCEManAdminC.c.obj
PaCE_Man_Admin_C.exe: CMakeFiles\PaCE_Man_Admin_C.dir\build.make
PaCE_Man_Admin_C.exe: CMakeFiles\PaCE_Man_Admin_C.dir\objects1.rsp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable PaCE_Man_Admin_C.exe"
	"C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe" -E vs_link_exe --intdir=CMakeFiles\PaCE_Man_Admin_C.dir --manifests  -- C:\PROGRA~2\MICROS~1\2017\COMMUN~1\VC\Tools\MSVC\1415~1.267\bin\Hostx86\x86\link.exe /nologo @CMakeFiles\PaCE_Man_Admin_C.dir\objects1.rsp @<<
 /out:PaCE_Man_Admin_C.exe /implib:PaCE_Man_Admin_C.lib /pdb:"C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug\PaCE_Man_Admin_C.pdb" /version:0.0  -static /debug /INCREMENTAL /subsystem:console kernel32.lib user32.lib gdi32.lib winspool.lib shell32.lib ole32.lib oleaut32.lib uuid.lib comdlg32.lib advapi32.lib 
<<

# Rule to build all files generated by this target.
CMakeFiles\PaCE_Man_Admin_C.dir\build: PaCE_Man_Admin_C.exe

.PHONY : CMakeFiles\PaCE_Man_Admin_C.dir\build

CMakeFiles\PaCE_Man_Admin_C.dir\clean:
	$(CMAKE_COMMAND) -P CMakeFiles\PaCE_Man_Admin_C.dir\cmake_clean.cmake
.PHONY : CMakeFiles\PaCE_Man_Admin_C.dir\clean

CMakeFiles\PaCE_Man_Admin_C.dir\depend:
	$(CMAKE_COMMAND) -E cmake_depends "NMake Makefiles" "C:\Users\OscarAraya\Desktop\PaCE Man Admin C" "C:\Users\OscarAraya\Desktop\PaCE Man Admin C" "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug" "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug" "C:\Users\OscarAraya\Desktop\PaCE Man Admin C\cmake-build-debug\CMakeFiles\PaCE_Man_Admin_C.dir\DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles\PaCE_Man_Admin_C.dir\depend

