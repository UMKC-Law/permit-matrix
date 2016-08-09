# permitme

[![Stories in Ready](https://badge.waffle.io/UMKC-Law/permit-matrix.png?label=ready&title=Ready)](https://waffle.io/UMKC-Law/permit-matrix)

Contractors in Kansas City find the permitting process cumbersome. It's difficult to understand which permits are required for a particular job and which departments need to be involved. The process of submitting permit applications can be time-consuming and often involves waiting in line, taking the contractors away from their core work. This project will explore how a mobile-first application might be able to streamline the process and reduce frustration. Key goals include:

* Reducing duplicate data entry
* Letting the application determine the required permits based on the contractor's description of the work
* Producing the required permits in PDF format for submission either in person or via email
* Making the process more transparent through plain English questions, contextual help and workflow aides that illustrate the process (e.g., showing which permits need to go to which department and why).

## Development Environment

Application Stack - https://jhipster.github.io

These installtion notes are taken from https://jhipster.github.io/installation/ and modified accordingly. If you run into issues please refer to the official documentation and update the README.md here with any fixes that are needed.

* Install Java 8 - http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Install Gradle - https://gradle.org/gradle-download/
* Install Git - http://git-scm.com/
* Install Node.js - http://nodejs.org
  * Install Yeoman - `npm install -g yo`
  * Install Bower - `npm install -g bower`
  * Install Gulp - `npm install -g gulp`
  * Install JHipster Generator - `npm install -g generator-jhipster`
* Install our npm modules - `npm install`

### Start the Development Server
`gradle`

If you want live reload capabilities with browser sync start the development server with `gulp` instead.
  
