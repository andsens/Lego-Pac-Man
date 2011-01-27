@pushd "%~dp0"
nxjc -cp .;..\..\Tools\src -d ..\bin %1%.java
@popd