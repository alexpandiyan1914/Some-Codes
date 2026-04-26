#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main()
{
    int fd1[2], fd2[2];
    pid_t  pid;
    char parent_msg[]  = "Hello from parent";
    char child_msg[] = "Hello from child";
    char read_msg[50];
    
    if(pipe(fd1) == -1){
        perror("pipe failed");
        return 1;
    }
    
    if(pipe(fd2) == -1){
        perror("pipe failed");
        return 1;
    }
    
    pid = fork();
    if(pid < 0){
        perror("fork failed");
        return 1;
    }
    
    if(pid > 0){
        close(fd1[0]);
        write(fd1[1], parent_msg, strlen(parent_msg) + 1);
        
        close(fd2[1]);
        read(fd2[0], read_msg, sizeof(read_msg));
        
        printf("parent received: %s\n", read_msg);
    }else{
        close(fd1[1]); 
        read(fd1[0], read_msg, sizeof(read_msg));
        printf("Child received: %s\n", read_msg);

        close(fd2[0]); 
        write(fd2[1], child_msg, strlen(child_msg) + 1);
    }
    
    return 0;
    
}
