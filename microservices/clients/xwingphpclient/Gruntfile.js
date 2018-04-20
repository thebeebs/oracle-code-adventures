module.exports = function(grunt) {
    require('load-grunt-tasks')(grunt);

    // Project configuration.
    grunt.initConfig({
        compress: {
            main: {
                options: {
                    archive: 'phpSkeleton.zip',
                    pretty: true
                },
                expand: true,
                cwd: './',
                src: ['index.php', 'manifest.json', 'start.sh'],
                dest: './'
            }
        }
    });
    // Default task(s).
    grunt.registerTask('default', ['compress']);
};