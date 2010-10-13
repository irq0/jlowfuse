/* example code from swig manual
 * multi-arg typemaps
 */
%typemap(in) (int argc, char **argv) {
    int i = 0;
    $1 = (*jenv)->GetArrayLength(jenv, $input);
    $2 = (char **) malloc(($1+1)*sizeof(char *));
    /* make a copy of each string */
    for (i = 0; i<$1; i++) {
        jstring j_string = (jstring)(*jenv)->GetObjectArrayElement(jenv, $input, i);
        const char * c_string = (*jenv)->GetStringUTFChars(jenv, j_string, 0);
        $2[i] = malloc((strlen(c_string)+1)*sizeof(char));
        strcpy($2[i], c_string);
        (*jenv)->ReleaseStringUTFChars(jenv, j_string, c_string);
        (*jenv)->DeleteLocalRef(jenv, j_string);
    }
    $2[i] = 0;
}

%typemap(freearg) (int argc, char **argv) {
    int i;
    for (i=0; i<$1-1; i++)
      free($2[i]);
    free($2);
}

%typemap(jni) (int argc, char **argv) "jobjectArray"
%typemap(jtype) (int argc, char **argv) "String[]"
%typemap(jstype) (int argc, char **argv) "String[]"

%typemap(javain) (int argc, char **argv) "$javainput"
