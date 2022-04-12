# Project 1: Reimbursement System
## Project Decription

The reimbursement system is designed to assist users in the management of work expenses.
Associates can submit reimbursement requests and view which reimbursements have been approved or denied.
It provides managers with the ability to approve or deny expense reimbursement requests.  
## Technoogies used
- Java 8
- Java Database Connectivity
- Javalin 4.3
- Mockito 4.3.1
- Google Cloub Platform
- PostgreSQL 14
- Logback Classic
- Json Web Token
- Firebase


## Features
### Managers
- Can Approve or Deny a reimbursement request
- Sort requests based on date and time of submission, status of request, type of request, and based on who reolved the request
- Filter requests based on status, type, author of request, who has resolved the request
### Associates
- Can submit requests for reimbursements.
- Upload images of receipts
- Sort requests based on date and time of submission, status of request, type of request, and based on who reolved the request

### To-do list:
- Reimbursements to be only visible to managers of associate instead of all reimbursements being visible to all managers
- Convert image link to open up as a modal to display reciept
- Access staff directory to be able to communicate via email

## Getting Started

The frontend of the application has been deployed to firebase while the backend logic is running on a Google Cloud Compute Engine.  

Firebase uses HTTP protocol while 

1. Using Chrome go to https://project-1-abel-220327-1.web.app/index.html 
2. If browser is set to HTTPS change settings to allow insecure content for this webpage.

        a. Click the lock button to the left of url
        b. Click site settings
        c. Change Insecure content to Allow
        d. Go back to homepage and click Crtl + Shift + r

## Usage
Once you are on the homepage you can log in as a user.

There are 2 types of users: 
        
        1.Managers
        2. Associates

The sign-up has not been setup on the front-end so to login please use the user provided below:

        1. Manager - username: Homer password: password
        2. Associate - username: RevManLoveJoy password: password


Once logged in you can access all reimbursements

if you are a manager you will see all users reimbursements

If you signin as an associate you will only see your reimbursements

Click get all reimbursements to see all user reimbursements

Once on the reimbursement page you will be able to filter reimbursements by type, status, and manager.  If you are signed in a manager you will also have he option to filter by associate.

You will also be able to sort through reimbursements by type, amount, status and when it was submitted.  

You will also be able to submitt reimbursements by clicking Add Reimbursement