#include "add.h"
#include <stdio.h>

int * add_1_svc(numbers *num, struct svc_req *rqstp)
{
    static int result;
    result = num->a + num->b;
    return &result;
}
