/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Daniela
 * Created: 23/04/2021
 */


INSERT INTO TYPE_PERMISSIONS (id, type_permission) 
VALUES (1, 'READ');
INSERT INTO TYPE_PERMISSIONS (id, type_permission) 
VALUES (2, 'WRITE');

INSERT INTO PERMISSIONS (id, album_id, user_id, type_permission_id) 
VALUES (1, 1, 2, 1);
INSERT INTO PERMISSIONS (id, album_id, user_id, type_permission_id) 
VALUES (2, 1, 3, 1);
INSERT INTO PERMISSIONS (id, album_id, user_id, type_permission_id) 
VALUES (3, 1, 4, 1);
INSERT INTO PERMISSIONS (id, album_id, user_id, type_permission_id) 
VALUES (4, 1, 4, 2);