import time
from platform import python_version

if python_version().startswith("3"):
    print("Test python3 script...waiting for 5 seconds...")
else:
    print "Test python2 script...waiting for 5 seconds..."
time.sleep(5)
exit(0)
