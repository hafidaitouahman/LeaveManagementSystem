export class pendingLeaveRequests {
    id!: number;
    requesterUsername!:string;
    approverUsername!:string;
    leaveTypeName!: string;
    duration!: number;
    from!: Date;
    to!: Date;
}