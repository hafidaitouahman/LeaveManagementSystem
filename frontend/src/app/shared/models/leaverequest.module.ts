export class LeaveRequest {
    leaveTypeId!:number;
    approverId!:number;
    replacementIds!: number[];
    startDate!:Date;
    endDate!:Date;
    comment!:string;


}