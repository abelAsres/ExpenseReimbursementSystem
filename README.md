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

![HomepageSecure](https://user-images.githubusercontent.com/55070709/163062628-3ae2c1c0-1347-4e34-822e-6bedaa9209a5.PNG)

2. If browser is set to HTTPS change settings to allow insecure content for this webpage.

        a. Click the lock button to the left of url
        b. Click site settings      
        c. Change Insecure content to Allow
        
        ![SecureSetAllow](https://user-images.githubusercontent.com/55070709/163062664-196ce9e6-378d-49d5-88c1-70a9545f9c7b.PNG)

        d. Go back to homepage and click Crtl + Shift + r

## Usage
Once you are on the homepage you can log in as a user.

There are 2 types of users: 
        
        1.Managers
        2. Associates

The sign-up has not been setup on the front-end so to login please use the user provided below:

        1. Manager - username: Homer password: password
        2. Associate - username: RevManLoveJoy password: password

![image](https://user-images.githubusercontent.com/55070709/163062770-1eee23eb-2c1c-4014-b14c-8d6e09d5e1ca.png)

Once logged in you can access all reimbursements

if you are a manager you will see all users reimbursements

If you signin as an associate you will only see your reimbursements

Click get all reimbursements to see all user reimbursements


![SeeAllReimbursements](https://user-images.githubusercontent.com/55070709/163062804-370805d8-7e69-4225-bc5a-8b22c32aefa9.PNG)

Once on the reimbursement page you will be able to filter reimbursements by type, status, and manager.  If you are signed in a manager you will also have he option to filter by associate.

![Filters](https://user-images.githubusercontent.com/55070709/163062886-5156e53f-cc31-4b6f-8a3e-6c48db244187.PNG)

You can clear filters by clicking the clear filters button

![Clear Filters](https://user-images.githubusercontent.com/55070709/163063410-cbd0f553-eade-4487-a554-470896365243.PNG)

You will also be able to sort through reimbursements by type, amount, status and when it was submitted.  

![Sort](https://user-images.githubusercontent.com/55070709/163062907-e89d41b3-17df-4b4a-92f6-551ac57cb8fb.PNG)

You will also be able to submitt reimbursements by clicking Add Reimbursement

![addReimbursementButton](https://user-images.githubusercontent.com/55070709/163062939-f7c491ad-b837-42c4-b5fd-972eb5a28ca6.PNG)

Provide reimbursement data and click submit

![FillOutForm](https://user-images.githubusercontent.com/55070709/163201985-b2372748-4b94-4869-9811-8a02f45044c4.PNG)

