package com.hoangphuc.todo.modules.tasklist;

import com.hoangphuc.todo.common.entity.BaseEntity;
import com.hoangphuc.todo.modules.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_lists")
@Getter
@Setter
@NoArgsConstructor
public class TaskList extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "is_default", nullable = false)
    private boolean defaultList = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}