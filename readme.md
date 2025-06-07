# DO NOT FORK!
**In the interest of fairness for everyone, please DO NOT fork this repo through Github. This is to avoid accidental sharing of the solution. If you are taking the test, please select "Use this template" to create your own repo and get started instead.**

# Restrictions
In this code test, we are trying to fairly judge **YOUR** coding, problem solving, and communication skills. Because of this, we ask that you **DO NOT use any AI Generative tooling** such as ChatGPT or Copilot to help you complete the coding assignment.

These tools are extremely beneficial and many of us use them in our day to day work, however an understanding of how the code being written functions is still required to make informed decisions around whether the generated code is appropriate for a given task.

# Background
Welcome to the Turnitin Code Test, congratulations on making it this far!

This test is a fictitious application made up of multiple components to represent the types of situations or problems you will encounter and have to solve within our team.

The applications is a simple web application that fetches a list of users and memberships and allows the user to search the memberships and view user details.

There are three components within the application, each with a different purpose and written in a different language.
* **react-frontend** - A simple react frontend written in TypeScript.
* **java-edge** - An edge service written as a Java Spring application that provides a rest API that can be called by the front end, and calls the backend service to fetch membership and user data.
* **php-backend** - A backend php service that provides user and membership data via an API. This data is stored in a Postgresql database.

To orchestrate running all parts of this service, docker and docker compose are used. See https://docs.docker.com/desktop/ for information on installing docker if you do not have it already.

# Tasks:
* Currently the java-edge service is making an API call per membership to fetch the user details. Refactor the java-edge service to use the `/api.php/users` endpoint to fetch all the users in one request rather than fetching the users individually.
  * Make sure to fix any broken tests.
  * Make sure any docs are updated appropriately.
  * The response from the java-edge service should not change.
* Searching for members by email is currently case sensitive. Update the search in the react-frontend to be case insensitive.
* The close (x) button on the user details modal doesn't currently function properly. Identify the problem and make the fix.

# Setup

1. Make sure you have installed Docker and Docker Compose
2. Run the following command from the root of the code test repo
```bash
docker-compose up --build
```
3. Go to http://localhost:8043 to run the application
4. If you have made changes to the code and want to rebuild the app with those latest changes, first stop the docker container (Ctrl+c) or `docker-compose down` then re-run the command from step 2

# Tests

To run the tests for the java-edge service, you can run the following command from the root of the code test repo
```bash
docker-compose -f docker-compose.test.yml up --build
```
