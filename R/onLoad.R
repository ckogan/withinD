.onLoad <- function(libname, pkgname){
  snapshot_path <-  "./java/withinD/target/withind-1.0-SNAPSHOT.jar"
  tryCatch({
    if(file.exists(snapshot_path)){
      print("Copying primary jar file")
      file.copy(snapshot_path,'./inst/java/withind-1.0-SNAPSHOT.jar',overwrite=T)
    } else {
      stop('Cannot find jar file')
    }
  }, error=function(e) {}
  )
  .jpackage(pkgname, lib.loc = libname)
}
