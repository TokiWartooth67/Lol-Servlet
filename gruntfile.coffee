# Grunt configuration updated to latest Grunt.  That means your minimum
# version necessary to run these tasks is Grunt 0.4.
#
# Please install this locally and install `grunt-cli` globally to run.

module.exports = (grunt) ->

  # Initialize the configuration.
  grunt.initConfig
    pkg: grunt.file.readJSON 'package.json'
    tomcat:
      options:
        docBase: "web"
    clean: [ "tomcat/webapps", "dist", "web/js", "web/css" ]
    mkdir:
      dist:
        options:
          create: [ "dist", "dist/WEB-INF", "dist/WEB-INF/classes" ]
    copy:
      web:
        files: [
          { expand: true, cwd: 'web/', src: [ '**/*.*' ], dest: 'dist/' }
        ]

    war:
      root:
        options:
          war_dist_folder: "tomcat/webapps"
          war_name: "ROOT"
          webxml: ->
            '''
              <?xml version="1.0" encoding="UTF-8" ?>
              <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">

              </web-app>
            '''
        files: [ { expand: true, cwd: 'dist', src: [ '**/*.*' ], dest: '' } ]
    run_java:
      javac_task:
        command: "javac",
        javaOptions:
          cp: [ "tomcat/lib/servlet-api.jar;tomcat/lib/json-simple-1.1.1.jar;tomcat/lib/javax.faces-api-2.2.jar;tomcat/lib/javax.servlet-api-3.1.0;tomcat/lib/commons-fileupload-1.3.jar;tomcat/lib/commons-io-2.5.jar;tomcat/lib/javax.mail.jar" ]
          d: "dist/WEB-INF/classes"

        sourceFiles: [


            "src/java/servlets/*.java"
            "src/java/servlets/api/*.java"
        ]


    coffee:
      compile:
        files: [
          expand: true
          cwd: 'src/coffee/'
          src: [ '**/*.coffee' ]
          dest: 'web/js/'
          ext: '.js'
          extDot: 'last'
        ]


    stylus:
      compile:
        files: [
          { 'web/css/main.css': 'src/stylus/main.styl' }
        ]

    concat:
      basic:
        src: [ 'dist/vendor/bootstrap/dist/css/bootstrap.css', 'dist/vendor/bootstrap/dist/css/bootstrap-theme.css', 'dist/vendor/gentelella/build/css/custom.min.css', 'dist/vendor/sweetalert/dist/sweetalert.css', 'dist/css/main.css' ],
        dest: 'dist/css/main.css'





  # Load external Grunt task plugins.
  grunt.loadNpmTasks 'grunt-shell'
  grunt.loadNpmTasks 'grunt-tomcat-developer'
  grunt.loadNpmTasks 'grunt-mkdir'
  grunt.loadNpmTasks 'grunt-contrib-clean'
  grunt.loadNpmTasks 'grunt-run-java'
  grunt.loadNpmTasks 'grunt-contrib-copy'
  grunt.loadNpmTasks 'grunt-war'
  grunt.loadNpmTasks 'grunt-contrib-coffee'
  grunt.loadNpmTasks 'grunt-contrib-stylus'
  grunt.loadNpmTasks 'grunt-contrib-concat'

  # Default task.
  grunt.registerTask "compile", [ "clean", "mkdir", "coffee", "stylus", "run_java:javac_task", "copy", "concat", "war" ]
  grunt.registerTask "start", [ "tomcat:start" ]