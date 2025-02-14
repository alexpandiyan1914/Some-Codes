#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

struct Activity{
    int start,end;
};

bool compare(Activity a,Activity b){
    return a.end < b.end;
}

void Activity_selection(vector<Activity>& activities){
    sort(activities.begin(),activities.end(),compare);
    cout<<"Selected Activities\n";
    int lastEndtime = 0;
    for(auto act : activities){
        if(act.start >= lastEndtime){
            cout<<"["<<act.start<<","<<act.end<<"]";
            lastEndtime = act.end;
        }
    }
}

int main(){
    int n;
    cout<<"Enter number of Activities:\n";
    cin>>n;
    vector<Activity> activities(n);
    
    cout<<"Enter start and End times:\n";
    for(int i=0;i<n;i++){
        cin>>activities[i].start>>activities[i].end;
    }
    
    Activity_selection(activities);
    return 0;
}
