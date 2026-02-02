#include "add.h"
#include <stdio.h>

int main(int argc, char *argv[])
{
    CLIENT *cl;
    numbers num;
    int *result;

    if (argc < 2) {
        printf("Usage: %s <server_ip>\n", argv[0]);
        return 1;
    }

    cl = clnt_create(argv[1], ADD_PROG, ADD_VERS, "udp");
    if (cl == NULL) {
        clnt_pcreateerror(argv[1]);
        return 1;
    }

    num.a = 10;
    num.b = 20;

    result = add_1(&num, cl);
    if (result == NULL) {
        clnt_perror(cl, "RPC call failed");
        return 1;
    }

    printf("Result from server: %d\n", *result);

    clnt_destroy(cl);
    return 0;
}
