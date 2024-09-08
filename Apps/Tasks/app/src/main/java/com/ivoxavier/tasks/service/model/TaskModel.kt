package com.ivoxavier.tasks.service.model

import com.google.gson.annotations.SerializedName


class TaskModel {
    var id: Int = 0

    @SerializedName("Id")
    var priorityId: Int = 0

    @SerializedName("Description")
    var description: String = ""

    @SerializedName("DueDate")
    var dueDate: String = ""

    @SerializedName("Complete")
    var complete: Boolean = false

    @SerializedName("PriorityId")
    var priorityDescription: String = ""

}