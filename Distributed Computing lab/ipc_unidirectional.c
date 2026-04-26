/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main()
{
    int fd[2];
    pid_t  pid;
    char write_msg[]  = "Hello from parent";
    char read_msg[50];
    
    if(pipe(fd) == -1){
        perror("pipe failed");
        return 1;
    }
    
    pid = fork();
    if(pid < 0){
        perror("fork failed");
        return 1;
    }
    
    if(pid > 0){
        close(fd[0]);
        write(fd[1], write_msg, strlen(write_msg)+1);
        close(fd[1]);
        wait(NULL);
    }else{
        close(fd[1]);
        read(fd[0], read_msg, sizeof(read_msg));
        printf("received from parent : %s\n", read_msg);
        close(fd[0]);
    }
    
    return 0;
    
}
