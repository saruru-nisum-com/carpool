module.exports = function(config) {
  config.set({
    basePath: '',
    frameworks: ['wiredep','jasmine'],
    files: [
      'src/main/resources/static/js/**/*.js',
      'src/main/resources/static/tests/**/*_test.js'
    ],
    exclude: [
    ],
    preprocessors: {},
    wiredep: {
        dependencies: true,    // default: true  
        devDependencies: true // default: false  
        //exclude: [ /jquery/, 'bower_components/modernizr/modernizr.js' ],
    },

    plugins : [ 'karma-phantomjs-launcher','karma-wiredep', 'karma-jasmine'],
    reporters: ['dots'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['PhantomJS'],
    singleRun: true
  });
};